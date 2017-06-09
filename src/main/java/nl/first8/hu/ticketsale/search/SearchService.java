package nl.first8.hu.ticketsale.search;

import nl.first8.hu.ticketsale.venue.Concert;
import nl.first8.hu.ticketsale.venue.ConcertRepository;
import nl.first8.hu.ticketsale.venue.Genre;
import nl.first8.hu.ticketsale.venue.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeroenvangelder on 7-6-17.
 */
@Service
public class SearchService {

    private final ConcertRepository concertRepository;

    @Autowired
    public SearchService(ConcertRepository concertRepository)
    {
        this.concertRepository = concertRepository;
    }

    public List<Concert> getByArtistName(String artistName)
    {
        return concertRepository.findByArtistName(artistName);
    }

    public List<Concert> getByArtistGenre(Genre genre)
    {
        return concertRepository.findByArtistGenre(genre);
    }

    public List<Concert> getByLocation(String location)
    {
        return concertRepository.findByLocation(location);
    }}
