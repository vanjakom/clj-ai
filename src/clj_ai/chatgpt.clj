(ns clj-ai.chatgpt
  (:require
   [clj-common.jvm :as jvm]
   [clj-http.client :as client]
   [clojure.data.json :as json]))

(def ^:dynamic openai-api-token (jvm/environment-variable "OPENAI_API_TOKEN_SF"))

(defn ask-openai
  "Sends a list of messages to the OpenAI ChatGPT API and returns the parsed 
  JSON response."
  [introduction prompt]
  (let [url "https://api.openai.com/v1/chat/completions"
        payload {
                 :model "gpt-4o" ;; or "gpt-4o-mini" / "gpt-4-turbo"
                 :messages [{:role "system"
                             :content introduction}
                            {:role "user"
                             :content prompt}]
                 :response_format {:type "json_object"}}
        headers {"Content-Type" "application/json"
                 "Authorization" (str "Bearer " openai-api-token)}
        response (json/read-str
                  (:body
                   (client/post url
                                {:headers headers
                                 :body (json/write-str payload)
                                 :as :text})))]
    (json/read-str (get-in response ["choices" 0 "message" "content"]))))

#_(ask-openai "Respond in json" "capital of France")

#_(ask-openai
 (str
  "Respond to question is given part electronical component or not with JSON "
  "{\"part\": \"yes/no\", \"term\":\"given term\", \"description\":\"one line"
  "part description\"}")
 "RMC10K22R0FTH"
 #_"ECHU1C104JB")
#_{
   "part" "yes",
   "term" "RMC10K22R0FTH",
   "description" "A surface-mount resistor with specific resistance value and tolerance"}
#_{
   "part" "yes",
   "term" "ECHU1C104JB",
   "description" "Multilayer ceramic chip capacitor used in electronic circuits"}
