## How to use ##
search engine app is a simple implementation of search engine. It uses embedded mongoDB to store information about indexed documents and terms. 
In memory implementation of DB loads about one minute.

## Endpoints ##
 *Index document*
  ----
    Index provided document. In case two documents with the same id are provided, only the first one will be indexed.
  
  * **Endpoint**
  
    /documents
  
  * **Method:**
  
    `POST`
    
  *  **Path Params**
  
        None
  
  * **Body**
  
    **Required:**
 
       `id=[string]`
   
       `content=[string]`
   
  * **Success Response:**
  
    * **Code:** 201 <br />
    
   * **Example call:**
   
        ```
       curl --location --request POST 'http://localhost:8090/documents' \
       --header 'Content-Type: application/json' \
       --data-raw '{
       	"id": "Document 1",
       	"content": "the red fox bit the lazy dog"
       }'
    
 
 -----------
 *Get documents for query*
  ----
    Get list of documents with their score (results are sorted descending using tf-idf score)
  
  * **Endpoint**
  
    /documents/{term}
  
  * **Method:**
  
    `GET`
    
  *  **Path Params**
  
        term - term which we'd like to search for in stored documents
  
  * **Success Response:**

    * **Code:** 200 <br />
    
    
  * **Example call:**
       
       ```
           curl --location --request GET 'http://localhost:8090/documents/fox' \
               --header 'Content-Type: application/json' \
               }'
 
   **Content:** 
   
      [
          {
              "id": "Document 3",
              "score": 0.025155894150811604
          },
          {
              "id": "Document 1",
              "score": 0.022011407381960155
          }
      ]