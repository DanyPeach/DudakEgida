package com.example.dudakegida.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String post;
    private LocalDateTime time;

    @Column(name = "part_post")
    private String partOfMessage;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
//    private String imgURL;

    @Column(name = "post_status")
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return post;
    }

    public void setMessage(String message) {
        this.post = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public String getImgURL() {
//        return imgURL;
//    }
//
//    public void setImgURL(String imgURL) {
//        this.imgURL = imgURL;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message message1 = (Message) o;

        if (getId() != null ? !getId().equals(message1.getId()) : message1.getId() != null) return false;
        if (getMessage() != null ? !getMessage().equals(message1.getMessage()) : message1.getMessage() != null)
            return false;
        if (getTime() != null ? !getTime().equals(message1.getTime()) : message1.getTime() != null) return false;
        return (getUser() != null ? !getUser().equals(message1.getUser()) : message1.getUser() == null);

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    //Класс сожержит след поля
    /*
    Время создания поста
    Само сообщение поста
    Картинка если есть
    Юзика

    Сервис доступен людям с ролью мембер. Чтобы ее получить нужно либо купить корма либо чет сделать
    Мемберы могут отправлять посты на три темы (енам - найдено животное, передердка, статьи)
    Должны быть сортирвка

    Есть кнопка - добавить пост, пишут пост, он доавляется на главную страницу. Создание поста типа регистрации форма
    Посты идут друг за другом
    Можно фильтровать. У постов есть кнопка отклика
     */
}
