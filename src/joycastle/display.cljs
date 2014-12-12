(ns joycastle.display)


(def ^:constant POSITION
  #{:TOP :RIGHT :BOTTOM :LEFT
    :TOP_LEFT :TOP_RIGHT :BOTTOM_RIGHT :BOTTOM_LEFT
    })

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

;(defn adjustNodeCenter
;  ([node]
;    (let [parent (.getParent node)]
;      (adjustNodeCenter node parent)))
;  ([node parent]
;    (let [parentSize (if (or (nil? parent) (.-width ) (.-winSize js/cc) () ))]))
;  )



;    adjustNodeCenter: function(node, parent){
;        var parentSize;
;        parent = parent || node.getParent();
;        if (parent){
;            parentSize = parent.getContentSize();
;            if (parentSize.width === 0 || parentSize.height === 0){
;                parentSize = cc.winSize;
;            }
;        } else {
;            parentSize = cc.winSize;
;        }
;        var offsetPos = cc.p((parentSize.width - node.width)/2, (parentSize.height - node.height)/2);
;        node.setPosition(cc.pAdd(node.getPosition(), offsetPos));
;        if (node.controller){
;            var ctl = node.controller;
;            if (typeof ctl.onNodeAdjusted === "function"){
;                ctl.onNodeAdjusted(node);
;            }
;        }
;    },
;;
;    adjustNodeWithScreenRelativeToParent: function(node, positionType, parent){
;        parent = parent || node.getParent();
;        var parentOffset = parent.getPosition();
;        var nodeOffset;
;        switch (positionType){
;            case this.POSITION.TOP:
;                nodeOffset = cc.p(0, parentOffset.y);
;                break;
;            case this.POSITION.RIGHT:
;                nodeOffset = cc.p(parentOffset.x, 0);
;                break;
;            case this.POSITION.BOTTOM:
;                nodeOffset = cc.p(0, -parentOffset.y);
;                break;
;            case this.POSITION.LEFT:
;                nodeOffset = cc.p(-parentOffset.x, 0);
;                break;
;            case this.POSITION.TOP_LEFT:
;                nodeOffset = cc.p(-parentOffset.x, parentOffset.y);
;                break;
;            case this.POSITION.TOP_RIGHT:
;                nodeOffset = cc.p(parentOffset.x, parentOffset.y);
;                break;
;            case this.POSITION.BOTTOM_RIGHT:
;                nodeOffset = cc.p(parentOffset.x, -parentOffset.y);
;                break;
;            case this.POSITION.BOTTOM_LEFT:
;                nodeOffset = cc.p(-parentOffset.x, -parentOffset.y);
;                break;
;        }
;        node.setPosition(cc.pAdd(node.getPosition(), nodeOffset));
;    },
;;
;    resizeToScreenRelativeToParent: function(node, parent){
;        parent = parent || node.getParent();
;        node.setContentSize(cc.winSize);
;        this.adjustNodeWithScreenRelativeToParent(node, this.POSITION.BOTTOM_LEFT, parent);
;    }