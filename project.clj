(defproject rhythm "0.1.0"
  :description "Write ClojureScript in Cocos2d-js."
  :url "http://joycastle.mobi"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2411"]
                 [figwheel "0.1.3-SNAPSHOT"]
                 ]
  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-figwheel "0.1.3-SNAPSHOT"]
            [lein-idea "1.0.1"]
            [com.cemerick/clojurescript.test "0.3.2"]] ;; <- Adding lein plugin for tests
  :jvm-opts ["-Xmx1G"]
  :figwheel {
             :http-server-root "public/"
             :port 3449
             }
  :cljsbuild {
              :builds[{:id "dev"
                       :source-paths ["src/rhythm" "src/figwheel" "src/cocos2d" "src/joycastle"]
                       :compiler {
                                  :output-to "resources/public/rhythm/src/game.js"
                                  :output-dir "resources/public/out"
                                  :optimizations :none
                                  :source-map true}
                       }
                      {:id "test"
                       :source-paths ["src/joycastle" "test"]
                       :notify-command ["phantomjs" :cljs.test/runner "resources/public/test/rhythm_test.js"]
                       :compiler {
                                  :output-to "resources/public/test/rhythm_test.js"
                                  :optimizations :whitespace
                                  }
                       }]

              }
  )