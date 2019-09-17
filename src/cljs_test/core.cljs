(ns cljs-test.core
  (:require [cljs.core.async :as async]))

(defn get-element
  [selector]
  (.querySelector js/document selector))

(defn click-chan
  [selector]
  (let [rc (async/chan)
        element (get-element selector)]
    (.addEventListener element "click"
                       (fn [] (async/put! rc [:clicked])))
    rc))

(defn app-loop
  []
  (let [button-click (click-chan "button")]
    (async/go
      (loop []
        (js/console.log (async/<! button-click))
        (recur)))))

(app-loop)