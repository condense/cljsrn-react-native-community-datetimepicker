(ns interop.react-native-community.datetimepicker.v1
  (:require ["@react-native-community/datetimepicker" :as module]
            [reagent.core :as r]))

(assert module)
(assert module/default)

(def datetimepicker (r/adapt-react-class module/default))

(comment

  (defn ios-picker
    [{:keys [value]}]
    (let [*value (r/atom value)]
      (fn [{:keys [visible onDismiss onChange]}]
        (let [value @*value]
          [rn/modal {:transparent true
                     :visible     visible}
           [rn/view {:flex 1}
            [rn/touchable-without-feedback {:onPress onDismiss}
             [rn/view {:style {:flex            1
                               :backgroundColor "rgba(0, 0, 0, 0.32)"}}]]
            [rn/view {:style {:flex            0
                              :maxHeight       250
                              :minHeight       250
                              :backgroundColor "white"}}
             [rn/button {:title "Select" :onPress #(onChange value)}]
             [datetimepicker/datetimepicker
              {:value    value
               :onChange (fn [_ d]
                           (reset! *value d))
               :mode     "date"}]]]]))))

  (defn android-picker
    [{:keys [value visible onDismiss onChange]}]
    (when visible
      [datetimepicker/datetimepicker
       {:value    value
        :onChange (fn [_ d]
                    (if-not (undefined? d)
                      (onChange d)
                      (onDismiss)))
        :mode     "date"}]))

  (def picker (if (= "ios" react-native/Platform.OS) ios-picker android-picker)))

