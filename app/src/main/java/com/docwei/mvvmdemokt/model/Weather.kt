package com.docwei.mvvmdemokt.model

class Weather {
    /**
     * aqi : {"city":{"aqi":"49","pm25":"33"}}
     * basic : {"city":"深圳","update":{"loc":"2020-03-29 17:12"},"id":"CN101280601"}
     * daily_forecast : [{"date":"2020-03-30","cond":{"txt_d":"多云"},"tmp":{"max":"8","min":"-1"}},{"date":"2020-03-31","cond":{"txt_d":"阴"},"tmp":{"max":"7","min":"-4"}},{"date":"2020-04-01","cond":{"txt_d":"多云"},"tmp":{"max":"6","min":"-3"}},{"date":"2020-04-02","cond":{"txt_d":"晴"},"tmp":{"max":"8","min":"-1"}},{"date":"2020-04-03","cond":{"txt_d":"晴"},"tmp":{"max":"7","min":"-4"}},{"date":"2020-04-04","cond":{"txt_d":"多云"},"tmp":{"max":"6","min":"-3"}}]
     * now : {"cond":{"txt":"多云"},"tmp":"6"}
     * status : ok
     * suggestion : {"cw":{"txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"comf":{"txt":"白天天气晴好，早晚会感觉偏凉，午后舒适、宜人。"},"sport":{"txt":"天气较好，无雨水困扰，较适宜进行各种运动，但因气温较低，在户外运动请注意增减衣物。"}}
     */
    lateinit var aqi: AqiBean
    lateinit var basic: BasicBean
    lateinit var now: NowBean
    lateinit var status: String
    lateinit var suggestion: SuggestionBean
    lateinit var daily_forecast: List<DailyForecastBean>

    class AqiBean {
        /**
         * city : {"aqi":"49","pm25":"33"}
         */
        lateinit var city: CityBean

        inner class CityBean {
            /**
             * aqi : 49
             * pm25 : 33
             */
            var aqi: String = ""
            var pm25: String = ""
        }
    }

    class BasicBean {
        /**
         * city : 深圳
         * update : {"loc":"2020-03-29 17:12"}
         * id : CN101280601
         */
        var city: String = ""
        lateinit var update: UpdateBean
        var id: String = ""

        inner class UpdateBean {
            /**
             * loc : 2020-03-29 17:12
             */
            var loc: String = ""
            fun time() = loc.split(" ")[1]
        }
    }

    class NowBean {
        /**
         * cond : {"txt":"多云"}
         * tmp : 6
         */
        lateinit var cond: CondBean
        var tmp: String = ""

        inner class CondBean {
            /**
             * txt : 多云
             */
            var txt: String? = null
        }
    }

    class SuggestionBean {
        /**
         * cw : {"txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
         * comf : {"txt":"白天天气晴好，早晚会感觉偏凉，午后舒适、宜人。"}
         * sport : {"txt":"天气较好，无雨水困扰，较适宜进行各种运动，但因气温较低，在户外运动请注意增减衣物。"}
         */
        lateinit var cw: CwBean
        lateinit var comf: ComfBean
        lateinit var sport: SportBean

        inner class CwBean {
            /**
             * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
             */
            lateinit var txt: String
        }

        inner class ComfBean {
            /**
             * txt : 白天天气晴好，早晚会感觉偏凉，午后舒适、宜人。
             */
            lateinit var txt: String
        }

        inner class SportBean {
            /**
             * txt : 天气较好，无雨水困扰，较适宜进行各种运动，但因气温较低，在户外运动请注意增减衣物。
             */
            lateinit var txt: String
        }
    }

    class DailyForecastBean {
        /**
         * date : 2020-03-30
         * cond : {"txt_d":"多云"}
         * tmp : {"max":"8","min":"-1"}
         */
        var date: String = ""
        lateinit var cond: CondBeanX
        lateinit var tmp: TmpBean

        inner class CondBeanX {
            /**
             * txt_d : 多云
             */
            var txt_d: String = ""
        }

        inner class TmpBean {
            /**
             * max : 8
             * min : -1
             */
            var max: String = ""
            var min: String? = ""
        }
    }
}