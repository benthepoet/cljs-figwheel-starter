(defproject cljs-test "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :profiles {:dev {:dependencies 
                   [[org.clojure/core.async "0.4.500"]
                    [org.clojure/clojurescript "1.10.339"]
                    [com.bhauman/figwheel-main "0.2.3"]
                    [com.bhauman/rebel-readline-cljs "0.1.4"]]}}
  :aliases {"fig" ["trampoline" "run" "-m" "figwheel.main"]})