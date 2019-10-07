(ns cljs-test.macros)

(defmacro create-element
  [tag & body]
  `(doto (.createElement js/document ~tag)
     ~@body))
