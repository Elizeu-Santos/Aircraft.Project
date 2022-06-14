package br.com.aircraft.demo.airport;

import java.util.ArrayList; // Imports
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aircraft.demo.entities.Aircraft;
import br.com.aircraft.demo.entities.Airline;

@RestController
public class TrafficController {

    private Long lastId;
    private List<Aircraft> listAircrafts;

    public TrafficController() { // New ArrayList TrafficController
        listAircrafts = new ArrayList<>();
        lastId = 1L;
    }

    @PostMapping("/aircraft") // POST Create new aircraft
    public ResponseEntity<Aircraft> create(@RequestBody Aircraft aircraft) {
        aircraft.setId(lastId);
        lastId++;
        this.listAircrafts.add(aircraft);
        return ResponseEntity.ok(aircraft);
    }

    @GetMapping("/aircrafts") // GET list all aircrafts
    public ResponseEntity<List<Aircraft>> findAll(
            @RequestParam(value = "airline", required = true) Airline airline) {
        if (airline != null) {
            List<Aircraft> airlineAircrafts = new ArrayList<>();
            for (int i = 0; i < listAircrafts.size(); i++) {
                if (listAircrafts.get(i).getAirline().equals(airline)) {
                    airlineAircrafts.add(listAircrafts.get(i));
                }
            }

            return ResponseEntity.ok(airlineAircrafts);
        }
        return ResponseEntity.ok(listAircrafts);
    }

    @GetMapping("/aircrafts/{id}") // GET por (ID)
    public ResponseEntity<Aircraft> find(@PathVariable Long id) {
        Aircraft aircraft;
        for (int i = 0; i < listAircrafts.size(); i++) {
            aircraft = listAircrafts.get(i);
            if (aircraft.getId() == id) {
                return ResponseEntity.ok(aircraft);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/aircraft/{id}") // Update Aircraft (ID)
    public ResponseEntity<Aircraft> update(@PathVariable Long id,
            @RequestBody Aircraft aircraft) {
        aircraft.setId(id);
        for (int i = 0; i < listAircrafts.size(); i++) {
            if (listAircrafts.get(i).getId().equals(aircraft.getId())) {
                listAircrafts.set(i,aircraft);

                return ResponseEntity.ok(aircraft);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/aircraft/{id}")  // Delete Aircraft (ID)
    public ResponseEntity<?> delete(@PathVariable Long id) {

        for(int i = 0; i < listAircrafts.size(); i++) {
            if (listAircrafts.get(i).getId().equals(id)) {
                listAircrafts.remove(i);
                
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
}