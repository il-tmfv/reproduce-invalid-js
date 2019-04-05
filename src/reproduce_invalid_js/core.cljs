(ns reproduce-invalid-js.core)

(enable-console-print!)

(def state (atom {}))

(def init-data
  (merge {:a nil :b nil} {}))

(defn render-app []
  (when init-data
    (swap! state merge init-data)))

(render-app)
