(ns joycastle.utils
  (:require [cocos2d.core :as cc]))

(defn isType [obj type]
  (identical? (goog/typeOf obj) type))

(defn isFunction [func]
  (identical? (goog/typeOf func) "function"))

(defn randomInt [start end]
  (let [length (- end start)]
    (+ (rand-int length) start)
    )
  )

(defn getRotatedWorldRect
  "Get rectangle of a node in world space even if it's rotated."
  [node]
  (if (isFunction (.-getQuad node))
    (let [quad (.getQuad node)
          result '( (cc/p (.-x .-vertices .-bl quad) (.-y .-vertices .-bl quad))
                    (cc/p (.-x .-vertices .-br quad) (.-y .-vertices .-br quad))
                    (cc/p (.-x .-vertices .-tr quad) (.-y .-vertices .-tr quad))
                    (cc/p (.-x .-vertices .-tl quad) (.-y .-vertices .-tl quad))
                    )]
      (map (.convertToWorldSpace node) result)
      )
    (let [width (* (.-width node) (.-scaleX node))
          height (* (.-height node) (.-scaleY node))
          result '( (cc/p 0 0)
                    (cc/p width 0)
                    (cc/p width height)
                    (cc/p 0 height)
                    )]
      (map (.convertToWorldSpace node) result)
      )
    )
  )

(defn convertPointsToWorld [node points]
  (map #(.convertToWorldSpace node %) points)
  )

(defn ^boolean pointInPolygon
  "Check whether a point is be inside a polygon."
  [point polyPoints]
  (let [results '()
        len (alength polyPoints)
        ]
    (loop [i 0]
      (when (< i len)
        (let [nextVertex (js-mod (inc i) len)]
          ;; Through cross product to get |a||b|sin(angle)
          (cons results (<
                          (cc/pCross (cc/pSub point (nth polyPoints i))
                                     (cc/pSub (nth polyPoints nextVertex) point)
                                     ) 0)
                ))
        (recur (inc i))
        )
      )
    (and results)
    )
  )

(defn ^boolean getWorldPosition
  [node]
  (let [localPos (.getAnchorPointInPoints node)]
    (.convertToWorldSpace node localPos)
    )
  )

(defn ^boolean getLocalPosition
  [node worldPos]
  (let [parent (.getParent node)]
    (if (nil? parent)
      nil
      (.convertToNodeSpace parent worldPos)
      )
    )
  )

(defn log
  [obj desc]
  (let [_desc (if (nil? desc) "" (str desc ": "))]
    (cc/log (str _desc (.stringify js/JSON obj nil 2)))
    )
  )


