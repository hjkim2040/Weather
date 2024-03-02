package zerobase.weather.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.weather.domain.Diary;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    List<Diary> findAllByDate(LocalDate date);
    List<Diary> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
    Diary getFirstByDate(LocalDate date);
    @Transactional
    void deleteAllByDate(LocalDate date);
}
