package com.wdp.Modal;

import com.wdp.Interface.SuperCastClass;

public class ProfileDataModal extends SuperCastClass {

    /**
     * success : true
     * message : null
     * data : {"current_user":{"id":11,"username":"Smith","name":"Smith123","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/original/1605764787300.jpg?1607085784"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/original/1605764787300.jpg?1607085784"},"background":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/011/thumb/DeepAR_2020_12_11_03_25_05.jpg?1609156940","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/011/medium/DeepAR_2020_12_11_03_25_05.jpg?1609156940","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/011/original/DeepAR_2020_12_11_03_25_05.jpg?1609156940"},"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-20T06:46:21.350Z","updated_at":"2020-12-28T12:09:26.896Z","bio":"testing","post_count":7,"follower_count":6,"following_count":6,"gender":null,"online":false,"website":"www.testing.com","phone":"testing","blocked":false,"family":null,"favourite":null}}
     */

    private String success;
    private String message;
    private DataBean data;

    public String isSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * current_user : {"id":11,"username":"Smith","name":"Smith123","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/original/1605764787300.jpg?1607085784"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/original/1605764787300.jpg?1607085784"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/original/1605764787300.jpg?1607085784"},"background":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/011/thumb/DeepAR_2020_12_11_03_25_05.jpg?1609156940","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/011/medium/DeepAR_2020_12_11_03_25_05.jpg?1609156940","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/011/original/DeepAR_2020_12_11_03_25_05.jpg?1609156940"},"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-20T06:46:21.350Z","updated_at":"2020-12-28T12:09:26.896Z","bio":"testing","post_count":7,"follower_count":6,"following_count":6,"gender":null,"online":false,"website":"www.testing.com","phone":"testing","blocked":false,"family":null,"favourite":null}
         */

        private CurrentUserBean current_user;

        public CurrentUserBean getCurrent_user() {
            return current_user;
        }

        public void setCurrent_user(CurrentUserBean current_user) {
            this.current_user = current_user;
        }

        public static class CurrentUserBean {
            /**
             * id : 11
             * username : Smith
             * name : Smith123
             * active : true
             * avatar : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/original/1605764787300.jpg?1607085784"}
             * avatar2 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/original/1605764787300.jpg?1607085784"}
             * avatar3 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/original/1605764787300.jpg?1607085784"}
             * avatar4 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/original/1605764787300.jpg?1607085784"}
             * avatar5 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/original/1605764787300.jpg?1607085784"}
             * avatar6 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/thumb/1605764787300.jpg?1607085784","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/medium/1605764787300.jpg?1607085784","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/original/1605764787300.jpg?1607085784"}
             * background : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/011/thumb/DeepAR_2020_12_11_03_25_05.jpg?1609156940","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/011/medium/DeepAR_2020_12_11_03_25_05.jpg?1609156940","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/011/original/DeepAR_2020_12_11_03_25_05.jpg?1609156940"}
             * followed_by_current_user : false
             * is_broadcasting : false
             * is_private : false
             * verified : false
             * created_at : 2020-11-20T06:46:21.350Z
             * updated_at : 2020-12-28T12:09:26.896Z
             * bio : testing
             * post_count : 7
             * follower_count : 6
             * following_count : 6
             * gender : null
             * online : false
             * website : www.testing.com
             * phone : testing
             * blocked : false
             * family : null
             * favourite : null
             */

            private String id;
            private String username;
            private String name;
            private String active;
            private AvatarBean avatar;
            private Avatar2Bean avatar2;
            private Avatar3Bean avatar3;
            private Avatar4Bean avatar4;
            private Avatar5Bean avatar5;
            private Avatar6Bean avatar6;
            private BackgroundBean background;
            private String followed_by_current_user;
            private String is_broadcasting;
            private String is_private;
            private String verified;
            private String created_at;
            private String updated_at;
            private String bio;
            private String post_count;
            private String follower_count;
            private String following_count;
            private String gender;
            private String online;
            private String website;
            private String phone;
            private String blocked;
            private String family;
            private String favourite;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String isActive() {
                return active;
            }

            public void setActive(String active) {
                this.active = active;
            }

            public AvatarBean getAvatar() {
                return avatar;
            }

            public void setAvatar(AvatarBean avatar) {
                this.avatar = avatar;
            }

            public Avatar2Bean getAvatar2() {
                return avatar2;
            }

            public void setAvatar2(Avatar2Bean avatar2) {
                this.avatar2 = avatar2;
            }

            public Avatar3Bean getAvatar3() {
                return avatar3;
            }

            public void setAvatar3(Avatar3Bean avatar3) {
                this.avatar3 = avatar3;
            }

            public Avatar4Bean getAvatar4() {
                return avatar4;
            }

            public void setAvatar4(Avatar4Bean avatar4) {
                this.avatar4 = avatar4;
            }

            public Avatar5Bean getAvatar5() {
                return avatar5;
            }

            public void setAvatar5(Avatar5Bean avatar5) {
                this.avatar5 = avatar5;
            }

            public Avatar6Bean getAvatar6() {
                return avatar6;
            }

            public void setAvatar6(Avatar6Bean avatar6) {
                this.avatar6 = avatar6;
            }

            public BackgroundBean getBackground() {
                return background;
            }

            public void setBackground(BackgroundBean background) {
                this.background = background;
            }

            public String isFollowed_by_current_user() {
                return followed_by_current_user;
            }

            public void setFollowed_by_current_user(String followed_by_current_user) {
                this.followed_by_current_user = followed_by_current_user;
            }

            public String isIs_broadcasting() {
                return is_broadcasting;
            }

            public void setIs_broadcasting(String is_broadcasting) {
                this.is_broadcasting = is_broadcasting;
            }

            public String isIs_private() {
                return is_private;
            }

            public void setIs_private(String is_private) {
                this.is_private = is_private;
            }

            public String isVerified() {
                return verified;
            }

            public void setVerified(String verified) {
                this.verified = verified;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getBio() {
                return bio;
            }

            public void setBio(String bio) {
                this.bio = bio;
            }

            public String getPost_count() {
                return post_count;
            }

            public void setPost_count(String post_count) {
                this.post_count = post_count;
            }

            public String getFollower_count() {
                return follower_count;
            }

            public void setFollower_count(String follower_count) {
                this.follower_count = follower_count;
            }

            public String getFollowing_count() {
                return following_count;
            }

            public void setFollowing_count(String following_count) {
                this.following_count = following_count;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String isOnline() {
                return online;
            }

            public void setOnline(String online) {
                this.online = online;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String isBlocked() {
                return blocked;
            }

            public void setBlocked(String blocked) {
                this.blocked = blocked;
            }

            public String getFamily() {
                return family;
            }

            public void setFamily(String family) {
                this.family = family;
            }

            public String getFavourite() {
                return favourite;
            }

            public void setFavourite(String favourite) {
                this.favourite = favourite;
            }

            public static class AvatarBean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/thumb/1605764787300.jpg?1607085784
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/medium/1605764787300.jpg?1607085784
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/011/original/1605764787300.jpg?1607085784
                 */

                private String thumb;
                private String medium;
                private String original;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }

            public static class Avatar2Bean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/thumb/1605764787300.jpg?1607085784
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/medium/1605764787300.jpg?1607085784
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/011/original/1605764787300.jpg?1607085784
                 */

                private String thumb;
                private String medium;
                private String original;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }

            public static class Avatar3Bean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/thumb/1605764787300.jpg?1607085784
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/medium/1605764787300.jpg?1607085784
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/011/original/1605764787300.jpg?1607085784
                 */

                private String thumb;
                private String medium;
                private String original;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }

            public static class Avatar4Bean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/thumb/1605764787300.jpg?1607085784
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/medium/1605764787300.jpg?1607085784
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/011/original/1605764787300.jpg?1607085784
                 */

                private String thumb;
                private String medium;
                private String original;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }

            public static class Avatar5Bean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/thumb/1605764787300.jpg?1607085784
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/medium/1605764787300.jpg?1607085784
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/011/original/1605764787300.jpg?1607085784
                 */

                private String thumb;
                private String medium;
                private String original;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }

            public static class Avatar6Bean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/thumb/1605764787300.jpg?1607085784
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/medium/1605764787300.jpg?1607085784
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/011/original/1605764787300.jpg?1607085784
                 */

                private String thumb;
                private String medium;
                private String original;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }

            public static class BackgroundBean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/011/thumb/DeepAR_2020_12_11_03_25_05.jpg?1609156940
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/011/medium/DeepAR_2020_12_11_03_25_05.jpg?1609156940
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/011/original/DeepAR_2020_12_11_03_25_05.jpg?1609156940
                 */

                private String thumb;
                private String medium;
                private String original;

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }
        }
    }
}
