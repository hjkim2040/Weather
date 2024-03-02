package zerobase.weather.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }


    @Operation(summary = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장")
    @PostMapping("/create/diary")
    public void createDiary(@RequestParam @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE) @Parameter(description = "조회할 기간",
            example = "2020-02-02") LocalDate date,
                            @RequestBody @Parameter(description = "일기 내용") String text) {
        diaryService.createDiary(date, text);
    }

    @Operation(summary = "선택한 날씨의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diary")
    public List<Diary> readDiary(@RequestParam @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE) @Parameter(description = "조회할 기간",
            example = "2020-02-02") LocalDate date) {
        return diaryService.readDiary(date);
    }

    @Operation(summary = "선택한 기간 사이의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diaries")
    public List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE) @Parameter(description = "조회할 기간의 첫번째날",
            example = "2020-02-02") LocalDate startDate,
                                   @RequestParam @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE) @Parameter(description = "조회할 기간의 마지막날",
                                           example = "2020-02-02") LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }
    @Operation(summary = "선택한 기간의 일기 데이터를 수정합니다.")
    @PutMapping("/update/diary")
    public void updateDiary(@RequestParam @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE) @Parameter(description = "조회할 기간",
            example = "2020-02-02") LocalDate date,
                            @RequestBody @Parameter(description = "일기 내용") String text) {
        diaryService.updateDiary(date, text);
    }
    @Operation(summary = "선택한 기간의 모든 일기 데이터를 삭제합니다.")
    @DeleteMapping("/delete/diary")
    public void deleteDiary(@RequestParam @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE) @Parameter(description = "조회할 기간",
            example = "2020-02-02") LocalDate date) {
        diaryService.deleteDiary(date);
    }
}
