(ns cocos2d.core)

(def log (.-log js/cc))

(defn p
  ([pos] (.p js/cc pos))
  ([x y] (.p js/cc x y))
  )

(def pCross (.-pCross js/cc))
(def pSub (.-pSub js/cc))


(defn ccset
  "Set properties for js objects."
  [args]
  ()
  )