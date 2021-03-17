package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;
import io.pivotal.pal.tracker.TimeEntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;
    }
@PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity<>(timeEntry, HttpStatus.CREATED);
    }
@GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        if (timeEntry==null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }
    }
@GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list = timeEntryRepository.list();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
@PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId,@RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry update = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        if(update==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
        return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
