(ns cljs-test.core
  (:require [cljs.core.async :as async]
            [cljs.reader :as reader])
  (:require-macros [cljs-test.macros :as macros]))

(defn build-response
  [request]
  {:body (.-responseText request)
   :status (.-status request)})

(defn http-request
  [url options]
  (let [channel (async/chan)
        request (js/XMLHttpRequest.)]
    (.open request (clj->js (:method options)) url true)
    (set! (.-onerror request)
          #(async/put! channel [:error :request-failed]))
    (set! (.-onload request)
          #(async/put! channel [:load (build-response request)]))
    (.send request (:body options))
    channel))

(defn http-get
  [url options]
  (http-request url (merge options {:method :get})))

(defn http-post
  [url options]
  (http-request url (merge options {:method :post})))

(def api-url "http://localhost:8300/todos")
