/*
 * Copyright 2018 GDG Community
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package de.berlindroid.droidcon.droidconcommunity

// TODO: 25/06/18 Can I haz data clazz?
class Session {

    var updatedDate: String? = null
    var type: String? = null
    var uri: String? = null
    var title: String? = null
    var label: String? = null
    var datetime: String? = null
    var startIso: List<String>? = null
    var endIso: List<String>? = null
    var roomId: List<String>? = null
    var room: List<String>? = null
    var speakerUids: List<String>? = null
    var speakerNames: List<String>? = null
    var categoryId: String? = null
    var category: String? = null
    var formatId: String? = null
    var format: String? = null
    var levelId: String? = null
    var level: String? = null
    var languageId: String? = null
    var language: String? = null
    var partnerIds: List<Any>? = null
    var partnerNames: List<Any>? = null
    var abstract: String? = null
    var video: List<Any>? = null
    var audio: List<Any>? = null
    var slide: List<Any>? = null
    var nid: String? = null

}
