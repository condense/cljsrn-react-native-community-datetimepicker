(ns interop.react-native-community.datetimepicker.v1-spec
  (:require [clojure.spec.alpha :as s]
            [react-native]))

(when (= react-native/Platform.OS "ios")
  (s/def ::mode #{"date" "time" "datetime" "countdown"})
  (s/def ::props (s/keys :req-un [::value]
                         :opt-un [::mode
                                  ::onChange
                                  ::maximumDate
                                  ::minimumDate
                                  ::textColor
                                  ::locale
                                  ::minuteInterval
                                  ::style])))

(when (= react-native/Platform.OS "android")
  (s/def ::mode #{"date" "time"})
  (s/def ::display #{"default" "spinner" "calendar" "clock"})
  (s/def ::props (s/keys :req-un [::value]
                         :opt-un [::mode
                                  ::display
                                  ::onChange
                                  ::maximumDate
                                  ::minimumDate
                                  ::timeZoneOffsetInMinutes
                                  ::timeZoneOffsetInSeconds
                                  ::dayOfWeekFormat
                                  ::dateFormat
                                  ::firstDayOfWeek
                                  ::is24Hour
                                  ::neutralButtonLabel])))

(when (= react-native/Platform.OS "window")
  (s/def ::mode #{"date"})
  (s/def ::props (s/keys :req-un [::value]
                         :opt-un [::mode
                                  ::onChange
                                  ::maximumDate
                                  ::minimumDate
                                  ::timeZoneOffsetInSeconds
                                  ::dayOfWeekFormat
                                  ::dateFormat
                                  ::firstDayOfWeek])))
