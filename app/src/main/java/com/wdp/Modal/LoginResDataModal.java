package com.wdp.Modal;

import com.wdp.Interface.SuperCastClass;

public class LoginResDataModal extends SuperCastClass {


    /**
     * success : true
     * message : Successfully loggedin
     * data : {"token":"eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxNCwiZXhwIjoxNjEyMjQ3NDc2fQ.WwKWBlyHat56QgF-zJScB-k0bMmhIWsnOrGP2cN2p64","exp":"12-05-2020 06:31","user":{"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/new-1.png?1607062930","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/new-1.png?1607062930","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/new-1.png?1607062930"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/logo-1-1.png?1607062931","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/logo-1-1.png?1607062931","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/logo-1-1.png?1607062931"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/IMG_20201127_104225.jpg?1607062931","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/IMG_20201127_104225.jpg?1607062931","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/IMG_20201127_104225.jpg?1607062931"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/IMG_20201115_124946.jpg?1607062932","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/IMG_20201115_124946.jpg?1607062932","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/IMG_20201115_124946.jpg?1607062932"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-04T06:22:15.929Z","bio":"test","post_count":4,"follower_count":4,"following_count":2,"gender":null,"online":false,"website":"www.test.com","phone":"7777788888","blocked":false,"family":null,"favourite":null}}
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
         * token : eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxNCwiZXhwIjoxNjEyMjQ3NDc2fQ.WwKWBlyHat56QgF-zJScB-k0bMmhIWsnOrGP2cN2p64
         * exp : 12-05-2020 06:31
         * user : {"id":14,"username":"vicky123","name":"Vicky","active":true,"avatar":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/new-1.png?1607062930","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/new-1.png?1607062930","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/new-1.png?1607062930"},"avatar2":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/logo-1-1.png?1607062931","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/logo-1-1.png?1607062931","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/logo-1-1.png?1607062931"},"avatar3":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/IMG_20201127_104225.jpg?1607062931","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/IMG_20201127_104225.jpg?1607062931","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/IMG_20201127_104225.jpg?1607062931"},"avatar4":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/IMG_20201115_124946.jpg?1607062932","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/IMG_20201115_124946.jpg?1607062932","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/IMG_20201115_124946.jpg?1607062932"},"avatar5":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"},"avatar6":{"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"},"background":null,"followed_by_current_user":false,"is_broadcasting":false,"is_private":false,"verified":false,"created_at":"2020-11-23T12:47:17.386Z","updated_at":"2020-12-04T06:22:15.929Z","bio":"test","post_count":4,"follower_count":4,"following_count":2,"gender":null,"online":false,"website":"www.test.com","phone":"7777788888","blocked":false,"family":null,"favourite":null}
         */

        private String token;
        private String exp;
        private UserBean user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExp() {
            return exp;
        }

        public void setExp(String exp) {
            this.exp = exp;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * id : 14
             * username : vicky123
             * name : Vicky
             * active : true
             * avatar : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/new-1.png?1607062930","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/new-1.png?1607062930","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/new-1.png?1607062930"}
             * avatar2 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/logo-1-1.png?1607062931","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/logo-1-1.png?1607062931","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/logo-1-1.png?1607062931"}
             * avatar3 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/IMG_20201127_104225.jpg?1607062931","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/IMG_20201127_104225.jpg?1607062931","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/IMG_20201127_104225.jpg?1607062931"}
             * avatar4 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/IMG_20201115_124946.jpg?1607062932","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/IMG_20201115_124946.jpg?1607062932","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/IMG_20201115_124946.jpg?1607062932"}
             * avatar5 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933"}
             * avatar6 : {"thumb":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934","medium":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934","original":"https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934"}
             * background : null
             * followed_by_current_user : false
             * is_broadcasting : false
             * is_private : false
             * verified : false
             * created_at : 2020-11-23T12:47:17.386Z
             * updated_at : 2020-12-04T06:22:15.929Z
             * bio : test
             * post_count : 4
             * follower_count : 4
             * following_count : 2
             * gender : null
             * online : false
             * website : www.test.com
             * phone : 7777788888
             * blocked : false
             * family : null
             * favourite : null
             */

            private String id;
            private String username;
            private String name;
            private String active;
            private AvatarBean avatar=null;
            private Avatar2Bean avatar2=null;
            private Avatar3Bean avatar3=null;
            private Avatar4Bean avatar4=null;
            private Avatar5Bean avatar5=null;
            private Avatar6Bean avatar6=null;
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

            public static class BackgroundBean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/thumb/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609140852
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/medium/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609140852
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/backgrounds/000/000/024/original/abstract-blue-geometric-shapes-background_1035-17545.jpg?1609140852
                 */

                private String thumb;
                private String medium;
                private String original;

                public BackgroundBean(String thumb, String medium, String original) {
                    this.thumb = thumb;
                    this.medium = medium;
                    this.original = original;
                }

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

            public static class AvatarBean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/thumb/new-1.png?1607062930
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/medium/new-1.png?1607062930
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatars/000/000/014/original/new-1.png?1607062930
                 */

                private String thumb="";
                private String medium;
                private String original;
                public AvatarBean(String thumb, String medium, String original) {
                    this.thumb = thumb;
                    this.medium = medium;
                    this.original = original;
                }
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
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/thumb/logo-1-1.png?1607062931
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/medium/logo-1-1.png?1607062931
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar2s/000/000/014/original/logo-1-1.png?1607062931
                 */

                private String thumb="";
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

                public Avatar2Bean(String thumb, String medium, String original) {
                    this.thumb = thumb;
                    this.medium = medium;
                    this.original = original;
                }
            }

            public static class Avatar3Bean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/thumb/IMG_20201127_104225.jpg?1607062931
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/medium/IMG_20201127_104225.jpg?1607062931
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar3s/000/000/014/original/IMG_20201127_104225.jpg?1607062931
                 */

                private String thumb="";
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

                public Avatar3Bean(String thumb, String medium, String original) {
                    this.thumb = thumb;
                    this.medium = medium;
                    this.original = original;
                }
            }

            public static class Avatar4Bean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/thumb/IMG_20201115_124946.jpg?1607062932
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/medium/IMG_20201115_124946.jpg?1607062932
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar4s/000/000/014/original/IMG_20201115_124946.jpg?1607062932
                 */

                private String thumb="";
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

                public Avatar4Bean(String thumb, String medium, String original) {
                    this.thumb = thumb;
                    this.medium = medium;
                    this.original = original;
                }
            }

            public static class Avatar5Bean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/thumb/IMG_20201128_172940.jpg?1607062933
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/medium/IMG_20201128_172940.jpg?1607062933
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar5s/000/000/014/original/IMG_20201128_172940.jpg?1607062933
                 */

                private String thumb="";
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

                public Avatar5Bean(String thumb, String medium, String original) {
                    this.thumb = thumb;
                    this.medium = medium;
                    this.original = original;
                }
            }

            public static class Avatar6Bean {
                /**
                 * thumb : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/thumb/IMG_20201113_181741.jpg?1607062934
                 * medium : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/medium/IMG_20201113_181741.jpg?1607062934
                 * original : https://s3.us-east-2.amazonaws.com/turnstr-prod/users/avatar6s/000/000/014/original/IMG_20201113_181741.jpg?1607062934
                 */

                private String thumb="";
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

                public Avatar6Bean(String thumb, String medium, String original) {
                    this.thumb = thumb;
                    this.medium = medium;
                    this.original = original;
                }
            }
        }
    }
}
