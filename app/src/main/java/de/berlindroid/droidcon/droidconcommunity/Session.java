
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

package de.berlindroid.droidcon.droidconcommunity;

import java.util.List;

// TODO: 25/06/18 Can I haz data clazz?
public class Session {

    private String updatedDate;
    private String type;
    private String uri;
    private String title;
    private String label;
    private List<Datetime> datetime = null;
    private List<String> startIso = null;
    private List<String> endIso = null;
    private List<String> roomId = null;
    private List<String> room = null;
    private List<String> speakerUids = null;
    private List<String> speakerNames = null;
    private String categoryId;
    private String category;
    private String formatId;
    private String format;
    private String levelId;
    private String level;
    private String languageId;
    private String language;
    private List<Object> partnerIds = null;
    private List<Object> partnerNames = null;
    private String _abstract;
    private List<Object> video = null;
    private List<Object> audio = null;
    private List<Object> slide = null;
    private String nid;

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Datetime> getDatetime() {
        return datetime;
    }

    public void setDatetime(List<Datetime> datetime) {
        this.datetime = datetime;
    }

    public List<String> getStartIso() {
        return startIso;
    }

    public void setStartIso(List<String> startIso) {
        this.startIso = startIso;
    }

    public List<String> getEndIso() {
        return endIso;
    }

    public void setEndIso(List<String> endIso) {
        this.endIso = endIso;
    }

    public List<String> getRoomId() {
        return roomId;
    }

    public void setRoomId(List<String> roomId) {
        this.roomId = roomId;
    }

    public List<String> getRoom() {
        return room;
    }

    public void setRoom(List<String> room) {
        this.room = room;
    }

    public List<String> getSpeakerUids() {
        return speakerUids;
    }

    public void setSpeakerUids(List<String> speakerUids) {
        this.speakerUids = speakerUids;
    }

    public List<String> getSpeakerNames() {
        return speakerNames;
    }

    public void setSpeakerNames(List<String> speakerNames) {
        this.speakerNames = speakerNames;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFormatId() {
        return formatId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Object> getPartnerIds() {
        return partnerIds;
    }

    public void setPartnerIds(List<Object> partnerIds) {
        this.partnerIds = partnerIds;
    }

    public List<Object> getPartnerNames() {
        return partnerNames;
    }

    public void setPartnerNames(List<Object> partnerNames) {
        this.partnerNames = partnerNames;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public List<Object> getVideo() {
        return video;
    }

    public void setVideo(List<Object> video) {
        this.video = video;
    }

    public List<Object> getAudio() {
        return audio;
    }

    public void setAudio(List<Object> audio) {
        this.audio = audio;
    }

    public List<Object> getSlide() {
        return slide;
    }

    public void setSlide(List<Object> slide) {
        this.slide = slide;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

}
