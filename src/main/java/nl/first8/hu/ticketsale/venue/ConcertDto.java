package nl.first8.hu.ticketsale.venue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jeroenvangelder on 8-6-17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcertDto {

    private String artist;

    private String genre;

    private String location;

}
