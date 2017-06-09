package nl.first8.hu.ticketsale.search;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.first8.hu.ticketsale.venue.ConcertDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jeroenvangelder on 7-6-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Rollback(false)
public class SearchIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testSearchOnName() throws Exception
    {
        String expectedResult = "Disturbed";

        MvcResult result = mvc.perform(
                get("/search/artist").param("artist_name", expectedResult)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        for(Iterator<ConcertDto> i = concerts(result).iterator(); i.hasNext();)
        {
            assertEquals(i.next().getArtist(), expectedResult);
        }

    }

    @Test
    public void testSearchOnGenre() throws Exception
    {
        String expectedResult = "metal";
        MvcResult result = mvc.perform(
                get("/search/genre").param("artist_genre", expectedResult).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        for(Iterator<ConcertDto> i = concerts(result).iterator(); i.hasNext();)
        {
            assertEquals(i.next().getGenre(), expectedResult);
        }
    }

    @Test
    public void testSearchOnLocation() throws Exception
    {
        String expectedResult = "Utrecht";

        MvcResult result = mvc.perform(
                get("/search/location").param("location", expectedResult).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        for(Iterator<ConcertDto> i = concerts(result).iterator(); i.hasNext();)
        {
            assertEquals(i.next().getLocation(), expectedResult);
        }
    }

    private List<ConcertDto> concerts(final MvcResult result) throws UnsupportedEncodingException, IOException {
        return objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ConcertDto>>() {
        });
    }
}
