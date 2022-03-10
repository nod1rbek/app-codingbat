package uz.pdp.app_codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarmupDto {
    private String name;
    private Integer taskId;
}
