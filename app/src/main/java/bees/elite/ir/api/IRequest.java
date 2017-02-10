package bees.elite.ir.api;

import java.util.Map;

/**
 * Created by Reza on 2/10/2017.
 */

public interface IRequest {
    public String getUrl();

    public Map<String , String> getParams();
}
