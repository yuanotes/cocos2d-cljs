(ns rhythm.core
  (:require  [cocos2d.core :as cc]
             [joycastle.utils :as utils]
             [cljs.core :as cljs :include-macros true])
  )


(cc/log (utils/isFunction #()))

(set!  (.-onStart (.-game js/cc))
       (fn []
         (.adjustViewPort (.-view js/cc) true)
         (.resizeWithBrowserSize (.-view js/cc) false)
         (.setDesignResolutionSize (.-view js/cc) 640 960 (.-FIXED_HEIGHT (.-ResolutionPolicy js/cc)))
         (.setResourcePath (.-BuilderReader js/cc) "res/")
         (.preload (.-LoaderScene js/cc) js/g_resources
                   (fn []
                     (let [])
                     ))))
(.run (.-game js/cc))
