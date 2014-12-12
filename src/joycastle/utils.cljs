(ns joycastle.utils)

(defn isType [obj type]
  (identical? (goog/typeOf obj) type))

(defn isFunction [func]
  (identical? (goog/typeOf func) "function"))

(defn randomInt [start end]
  (let [length (- end start)]
    (+ (rand-int length) start)
    )
  )



