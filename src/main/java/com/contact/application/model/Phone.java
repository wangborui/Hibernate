package com.contact.application.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Phone {
    @Id
    @Column
    private String id;
    @Column
    @NotNull
    private String phoneNumber;
    @Column
    @NotNull
    private String customType;
    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;
    @Column
    private boolean deleted;

    @ManyToMany(mappedBy = "phoneList", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Contact> contacts;

    public Phone(String id, String phoneNumber, String customType, PhoneType phoneType, boolean deleted) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.customType = customType;
        this.phoneType = phoneType;
        this.deleted = deleted;
    }
}
