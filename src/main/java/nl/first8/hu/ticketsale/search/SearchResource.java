package nl.first8.hu.ticketsale.search;

import nl.first8.hu.ticketsale.venue.Concert;
import nl.first8.hu.ticketsale.venue.ConcertDto;
import nl.first8.hu.ticketsale.venue.Genre;
import nl.first8.hu.ticketsale.venue.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jeroenvangelder on 7-6-17.
 */
@RestController
@RequestMapping("/search")
@Transactional
public class SearchResource {

    private final SearchService service;
    @Autowired
    public SearchResource(SearchService service){ this.service = service; }

    @GetMapping(path = "/artist")
    public ResponseEntity getConcertByArtistName(@RequestParam("artist_name") final String artistName)
    {
        try{
            List<Concert> concerts = service.getByArtistName(artistName);
            List<ConcertDto> responseConcerts = getConcertDtos(concerts);
            return ResponseEntity.ok(responseConcerts);
        } catch (RuntimeException e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping(path = "/genre")
    public ResponseEntity getConcertByGenre(@RequestParam("artist_genre") final String artistGenre)
    {
        Genre genre = Genre.valueOf(artistGenre);
        try{
            List<Concert> concerts = service.getByArtistGenre(genre);
            List<ConcertDto> responseConcerts = getConcertDtos(concerts);
            return ResponseEntity.ok(responseConcerts);
        } catch (RuntimeException e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping(path = "/date")
    public ResponseEntity getConcertByMinimumDate(@RequestParam("minimum_date") final String minDate)
    {
        try{
            return ResponseEntity.ok(new Concert());
        } catch (RuntimeException e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping(path = "/location")
    public ResponseEntity getConcertByLocation(@RequestParam("location") final String location)
    {
        try{
            List<Concert> concerts = service.getByLocation(location);
            List<ConcertDto> responseConcerts = getConcertDtos(concerts);
            return ResponseEntity.ok(responseConcerts);        } catch (RuntimeException e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    private List<ConcertDto> getConcertDtos(List<Concert> concerts) {
        return concerts.stream()
                .map(c -> new ConcertDto(c.getArtist().getName(), c.getArtist().getGenre().toString(),
                        c.getLocation().getName()))
                .collect(Collectors.toList());
    }
}