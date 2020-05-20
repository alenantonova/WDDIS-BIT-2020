package hello.DataBase;

import javax.persistence.*;

@Table(name = "application_weather", schema = "psgtable")
@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private long time;
    private Double temperature;

    public Weather() {}

    public Weather(long init_time, Double init_temperature) {
        time = init_time;
        temperature = init_temperature;
    }

    public long getTime() {return time;}
    public Double getTemperature() {return temperature;}
}
