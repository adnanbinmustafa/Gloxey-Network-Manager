# Gloxey Network Manager

This library aims to help developers in setting up [Volley](https://developer.android.com/training/volley/index.html) for rest api calls. It also provides different usefull methods about network connectivity to save development time.

#### Gradle Dependency

        dependencies { 

        compile 'io.gloxey.gnm:network-manager:1.0.1'

        }
        
        
### ConnectionManager.class

| Method | Description   |
| ------------- |-------------|
| isNetwork     | Returns **true** if device is connected with internet otherwise **false**. |
| getConnectedNetworkName     | Returns name of connected network or null if not connected. |
| isWifiConnected  | Returns **true** if connected to wifi otherwise **false**. |
| isMobileConnected | Returns **true** if connected to Mobile Data otherwise **false**. |
| volleyStringRequest | To make volley string request using GET, POST, PUT, DELETE method with or without header. |
| volleyJSONRequest | To make volley JSON request using GET, POST, PUT, DELETE method with or without header. |


### ConnectionDetector.class

| Callback | Description   |
| ------------- |-------------|
| ConnectionReceiverListener.isNetwork   | Returns **true** if device is connected with internet otherwise **false**. |

Feel free to implement this listener on activity or fragment to receive broadcast about network change. It will notifiy you that whether network is connected or disconnected.

**Example**

      public class MainActivity extends AppCompatActivity implements ConnectionDetector.ConnectionReceiverListener {
    
      @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      }
      
      @Override
      protected void onResume() {
          ConnectionDetector.getInstance().setConnectionReceiverListener(this);
        super.onResume();
     }
    
      @Override
      public void isNetwork(boolean isConnected) {

        if (isConnected) {
            Toast.makeText(this, "Network Connected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Network Disconnected", Toast.LENGTH_SHORT).show();
        }
       }
    
    }
    

# Supported Request Types.
 
## 1. [Volley](https://developer.android.com/training/volley/index.html) StringRequest

#### 1.1 Method GET (without header)
 
         ConnectionManager.volleyStringRequest(context, isDialog, progressView, requestURL, volleyResponseInterface);       
         
#### 1.2 Method GET (with header)
 
         ConnectionManager.volleyStringRequest(context, isDialog, progressView, requestURL, header, volleyResponseInterface);       
       
#### 1.3 Method POST/PUT/DELETE (without header)

         ConnectionManager.volleyStringRequest(context, isDialog, progressView, requestURL, requestMethod, params, volleyResponseInterface);        

#### 1.4 Method POST/PUT/DELETE (with header)

         ConnectionManager.volleyStringRequest(context, isDialog, progressView, requestURL, requestMethod, params, header, volleyResponseInterface);        

## 2. [Volley](https://developer.android.com/training/volley/index.html) JSONRequest

#### 2.1 Method GET (without header)
        
         ConnectionManager.volleyJSONRequest(context, isDialog, progressView, requestURL, volleyResponseInterface);

#### 2.2 Method GET (with header)
        
         ConnectionManager.volleyJSONRequest(context, isDialog, progressView, requestURL, header, volleyResponseInterface);

#### 2.3 Method POST/PUT/DELETE (without header)

          ConnectionManager.volleyJSONRequest(context, isDialog, progressView, requestURL, requestMethod, jsonParamObject, volleyResponseInterface);    
          
#### 2.4 Method POST/PUT/DELETE (with header)

          ConnectionManager.volleyJSONRequest(context, isDialog, progressView, requestURL, requestMethod, jsonParamObject, header, volleyResponseInterface);        
          

# How to use?

| Configuration | Description   |
| ------------- |-------------|
| context       | context |
| isDialog      | If **true** dialog will appear, otherwise not. Special thanks to [ACProgressLite](https://github.com/Cloudist/ACProgressLite) .       |
| progressView  | For custom progress view supply your progress view id and make **isDialog** true. otherwise pass null.|
|requestURL|Pass your API URL.|
|volleyResponseInterface| Callback for response in case of Volley.|
        
# Sample Code

## [Volley](https://developer.android.com/training/volley/index.html) StringRequest

#### 1.1 Method GET (without header)

        ConnectionManager.volleyStringRequest(context, isDialog, progressDialogView, requestURL, volleyResponseInterface);

**Example 1.1.1**     
   
 If you don't want to show progressbar. 
         
        ConnectionManager.volleyStringRequest(this, false, null, "url", new VolleyResponse() {
            @Override
            public void onResponse(String _response) {

                /**
                 * Handle Response
                 */
            }

            @Override
            public void onErrorResponse(VolleyError error) {

                /**
                 * handle Volley Error
                 */
            }

            @Override
            public void isNetwork(boolean connected) {

                /**
                 * True if internet is connected otherwise false
                 */
            }
        });


**Example 1.1.2**     
   
 If you want to show progressbar.  
   
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        
        ConnectionManager.volleyStringRequest(this, true, progressBar, "url", new VolleyResponse() {
            @Override
            public void onResponse(String _response) {

                /**
                 * Handle Response
                 */
            }

            @Override
            public void onErrorResponse(VolleyError error) {

                /**
                 * handle Volley Error
                 */
            }

            @Override
            public void isNetwork(boolean connected) {

                /**
                 * True if internet is connected otherwise false
                 */
            }
        });


**Example 1.1.3**     
   
   Library default progressbar. 

    
           ConnectionManager.volleyStringRequest(this, true, null, "url", new VolleyResponse() {
            @Override
            public void onResponse(String _response) {

                /**
                 * Handle Response
                 */
            }

            @Override
            public void onErrorResponse(VolleyError error) {

                /**
                 * handle Volley Error
                 */
            }

            @Override
            public void isNetwork(boolean connected) {

                /**
                 * True if internet is connected otherwise false
                 */
            }
        });

#### 1.1 Method GET (with header)

        ConnectionManager.volleyStringRequest(context, isDialog, progressDialogView, requestURL, header, volleyResponseInterface);


## [Volley](https://developer.android.com/training/volley/index.html) StringRequest

#### 1.2 Method POST/PUT/DELETE (without header)

        ConnectionManager.volleyStringRequest(context, isDialog, progressDialogView, requestURL, requestMethod, params, volleyResponseInterface);
        
**Example 1.2.1**        
        
        Use Method : Request.Method.POST
                     Request.Method.PUT
                     Request.Method.DELETE
                     
        Your params.
        HashMap<String, String> params = new HashMap<>();
        params.put("param 1", "value");
        params.put("param 2", "value");
        
        ConnectionManager.volleyStringRequest(getParent(), true, null, "url", Request.Method.POST, params, new VolleyResponse() {
            @Override
            public void onResponse(String _response) {

                /**
                 * Handle Response
                 */
            }

            @Override
            public void onErrorResponse(VolleyError error) {

                /**
                 * handle Volley Error
                 */
            }

            @Override
            public void isNetwork(boolean connected) {

                /**
                 * True if internet is connected otherwise false
                 */
            }
        });

## [Volley](https://developer.android.com/training/volley/index.html) StringRequest

#### 1.3 Method POST/PUT/DELETE (with header)

                ConnectionManager.volleyStringRequest(context, isDialog, progressDialogView, requestURL, requestMethod,params,header, volleyResponseInterface);
        
**Example 1.3.1**        
        
        Use Method : Request.Method.POST
                     Request.Method.PUT
                     Request.Method.DELETE
                    
        your params.
        HashMap<String, String> params = new HashMap<>();
        params.put("param 1", "value");
        params.put("param 2", "value");

        your Header.
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("apiKey", "xxxxxxxxxxxxxxx");

        
        ConnectionManager.volleyStringRequest(getParent(), true, null, "url", Request.Method.POST, params,header, new VolleyResponse() {
            @Override
            public void onResponse(String _response) {

                /**
                 * Handle Response
                 */
            }

            @Override
            public void onErrorResponse(VolleyError error) {

                /**
                 * handle Volley Error
                 */
            }

            @Override
            public void isNetwork(boolean connected) {

                /**
                 * True if internet is connected otherwise false
                 */
            }
        });


## [Volley](https://developer.android.com/training/volley/index.html) JSONRequest

#### 2.1 Method POST/PUT/DELETE (with header)

        ConnectionManager.volleyJSONRequest(context, isDialog, progressDialogView, requestURL, requestMethod,jsonParamsObject,header, volleyResponseInterface);
        
        Method is same as above
        
        
#### 2.2 Method POST/PUT/DELETE (without header)


        ConnectionManager.volleyJSONRequest(context, isDialog, progressDialogView, requestURL,jsonParamsObject,requestMethod, volleyResponseInterface);
        
        Method is same as above

#### 2.3 Method GET (without header)

        ConnectionManager.volleyJSONRequest(context, isDialog, progressDialogView, requestURL, volleyResponseInterface);
        
        Method is same as above

#### 2.4 Method GET (with header)

        ConnectionManager.volleyJSONRequest(context, isDialog, progressDialogView, requestURL, header,  volleyResponseInterface);
        
        Method is same as above


# Bonus
#### Gloxey JSONParser
Feel free to use gloxey json parser to parse your api response.

**Example.**

      YourModel yourModel = GloxeyJsonParser.getInstance().parse(_response, YourModel.class);
      


