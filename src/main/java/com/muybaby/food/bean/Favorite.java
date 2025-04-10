import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
    private Long favoriteId;
    private Long userId;
    private Long dishId;
}