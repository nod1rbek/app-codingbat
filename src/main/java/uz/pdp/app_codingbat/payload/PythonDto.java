package uz.pdp.app_codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PythonDto {

    private Integer warmupId;

    private Integer listId;

    private Integer logicId;
}
