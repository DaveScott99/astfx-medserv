package com.daverj.media.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@DiscriminatorValue("movie")
public class Movie extends Media { }