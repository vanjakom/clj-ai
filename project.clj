(defproject
  com.mungolab/clj-ai
  "0.1.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :deploy-repositories [
                        ["clojars" {
                                    :url "https://clojars.org/repo"
                                    :sign-releases false}]]  
  :dependencies [
                 [org.clojure/clojure "1.11.1"]
                 [com.mungolab/clj-common "0.3.3"]])
