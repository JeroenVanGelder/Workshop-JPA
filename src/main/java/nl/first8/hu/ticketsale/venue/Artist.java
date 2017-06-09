package nl.first8.hu.ticketsale.venue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jeroenvangelder on 30-5-17.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Genre genre;

    public Artist(String name, Genre genre)
    {
        this.genre = genre;
        this.name = name;
    }

}
