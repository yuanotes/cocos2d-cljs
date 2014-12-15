(ns joycastle.display
  (:require [cocos2d.core :as cc]
            [joycastle.utils :as utils]))


(def POSITION
  (zipmap
    [:TOP :RIGHT :BOTTOM :LEFT
     :TOP_LEFT :TOP_RIGHT :BOTTOM_RIGHT :BOTTOM_LEFT
     ] (range))
  )




(defn setResolutionDesign
  [designWidth designHeight]
  (let [rWidth (.-width (.-winSize js/cc))
        rHeight (.-height (.-winSize js/cc))]
    (if (rWidth > rHeight)
      (.setDesignResolutionSize (.-view js/cc) designWidth designHeight (.-FIXED_HEIGHT (.-ResolutionPolicy js/cc)))
      (.setDesignResolutionSize (.-view js/cc) designWidth designHeight (.-FIXED_WIDTH  (.-ResolutionPolicy js/cc)))
      )
    )
  )

(def setPortraitResolutionDesign
  (setResolutionDesign 640 960))

(def setLandScapeResolutionDesign
  (setResolutionDesign 960 640))

(defn adjustNodeCenter
  ([node]
    (let [parent (.getParent node)]
      (adjustNodeCenter node parent)))
  ([node parent]
    (let [parentSize (if (nil? parent)
                       cc/winSize
                       (if (some #(identical? 0 %) '((.-width parent) (.-height) parent))
                         cc/winSize
                         (.getContentSize parent)
                         )
                       )
          half #(/ (%1 - %2) 2)
          offsetPos (cc/p (half (.-width parentSize) (.-width node))
                          (half (.-height parentSize) (.-height node)))
          nodeNewPos (cc/pAdd (.getPosition node) offsetPos)
          ]
      (.setPosition node nodeNewPos)
      (if (not (nil? (.-controller node)))
        (if (utils/isFunction (.-onNodeAdjusted (.-controller node)))
          (.onNodeAdjusted (.-controller node) node))
        )
      )
    )
  )



(defn adjustNodeWithScreenRelativeToParent
  [node positionType parent]
  (let [realParent (if (nil? parent) (.getParent node) parent)
        parentOffset (.getPosition parent)
        x (.-x parentOffset)
        y (.-y parentOffset)
        nodeOffset (condp = positionType
                     (:TOP POSITION)   (cc/p 0 y)
                     (:RIGHT POSITION)  (cc/p x 0)
                     (:BOTTOM POSITION)   (cc/p 0 (- y))
                     (:LEFTP POSITION)    (cc/p (- x) 0)
                     (:TOP_LEFT POSITION)  (cc/p (- x) y)
                     (:TOP_RIGHT  POSITION) (cc/p x y)
                     (:BOTTOM_RIGHT  POSITION) (cc/p x (- y))
                     (:BOTTOM_LEFT POSITION)  (cc/p (- x) (- y)))]
    (.setPosition node (cc/pAdd (.getPosition node) nodeOffset))
    )
  )

(defn resizeToScreenRelativeToParent
  [node parent]
  (let [realParent (if (nil? parent) (.getParent node) parent)]
    (.setContentSize node (cc/winSize))
    (adjustNodeWithScreenRelativeToParent node (:BOTTOM_LEFT POSITION) realParent)
    )
  )
