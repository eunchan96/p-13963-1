package com.back.domain.wiseSaying.wiseSaying.controller;

import com.back.domain.wiseSaying.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.wiseSaying.service.WiseSayingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class WiseSayingController {
    private final WiseSayingService wiseSayingService;

    @GetMapping("/wiseSayings/write")
    @ResponseBody
    public String write(@RequestParam(defaultValue = "내용") String content, @RequestParam(defaultValue = "작가")String author) {
        if(content == null || content.isBlank()) {
            throw new IllegalArgumentException("content is null or blank");
        }
        if(author == null || author.isBlank()) {
            throw new IllegalArgumentException("author is null or blank");
        }

        WiseSaying wiseSaying = wiseSayingService.write(content, author);
        return "%d번 명언이 생성되었습니다. ".formatted(wiseSaying.getId());
    }

    @GetMapping("/wiseSayings")
    @ResponseBody
    public String list() {
        return "<ul>"
                + wiseSayingService.findAll().stream()
                .map(wiseSaying -> "<li>%d / %s / %s</li>".formatted(wiseSaying.getId(), wiseSaying.getContent(), wiseSaying.getAuthor()))
                .collect(Collectors.joining())
                 +"</ul>";
    }

    @GetMapping("/wiseSayings/{id}/delete")
    @ResponseBody
    public String delete(@PathVariable int id) {
        WiseSaying wiseSaying = wiseSayingService.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 명언이 존재하지 않습니다."));

        wiseSayingService.delete(wiseSaying);
        return "%d번 명언이 삭제되었습니다.".formatted(id);
    }

    @GetMapping("/wiseSayings/{id}/modify")
    @ResponseBody
    public String modify(@PathVariable int id, @RequestParam(defaultValue = "내용") String content, @RequestParam(defaultValue = "작가") String author) {
        WiseSaying wiseSaying = wiseSayingService.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID의 명언이 존재하지 않습니다."));

        if(content == null || content.isBlank()) {
            throw new IllegalArgumentException("content is null or blank");
        }
        if(author == null || author.isBlank()) {
            throw new IllegalArgumentException("author is null or blank");
        }

        wiseSayingService.modify(wiseSaying, content, author);
        return "%d번 명언이 수정되었습니다.".formatted(id);
    }

    @GetMapping("/wiseSayings/{id}")
    @ResponseBody
    public String detail(@PathVariable int id) {
        WiseSaying wiseSaying = wiseSayingService.findById(id).get(); // 이렇게 해도 알아서 예외를 던져줌

        return """
                <h1>%d번 명언</h1>
                <div>작가 : %s</div>
                <div>내용 : %s</div>
                """.formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
    }
}
