package nl.first8.hu.ticketsale.venue;

import nl.first8.hu.ticketsale.sales.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by jeroenvangelder on 7-6-17.
 */

@Repository
public class ConcertRepository {

    private final EntityManager entityManager;

    @Autowired
    public ConcertRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Concert> findByArtistName(String artistName)
    {
        String jpql = "SELECT c FROM Concert c, Artist a WHERE c.artist = a.id AND a.name =:artistname";
        TypedQuery<Concert> query = entityManager.createQuery(jpql, Concert.class);
        query.setParameter("artistname", artistName);
        return query.getResultList();
    }

    public List<Concert> findByArtistGenre(Genre genre)
    {
        String jpql = "SELECT c FROM Concert c, Artist a WHERE c.artist = a.id AND a.genre =:genre";
        TypedQuery<Concert> query = entityManager.createQuery(jpql, Concert.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    public List<Concert> findByLocation(String location)
    {
        String jpql = "SELECT c FROM Concert c, Location l WHERE l.name =:location AND c.location = l.id";
        TypedQuery<Concert> query = entityManager.createQuery(jpql, Concert.class);
        query.setParameter("location", location);
        return query.getResultList();
    }
}
