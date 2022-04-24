package io.gloxey.gnm.parser;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GloxeyJsonParser {

    private static GloxeyJsonParser mInstance;
    private static final String ERROR_MESSAGE = "Invalid JSON Exception, Parsing Error";

    public static synchronized GloxeyJsonParser getInstance() {
        if (mInstance == null) {
            mInstance = new GloxeyJsonParser();
        }
        return mInstance;
    }


    /**
     * Generic method to parse all type of models
     * Simply pass Model and Response and get parsed model :D
     * Yahoo..........
     * <p>
     * LoginResponse loginResponse= GloxeyJsonParser.getInstance().parse(_response,LoginResponse.class);
     *
     * @param _response
     * @param _model
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T parse(String _response, Class<T> _model) throws Exception {
        T parsedObject = null;

        try {
            if (_response != null) {
                Gson gson = new Gson();
                parsedObject = gson.fromJson(_response, _model);
            }
        } catch (JsonSyntaxException _jse) {
            throw new Exception(ERROR_MESSAGE);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return parsedObject;
    }


}
