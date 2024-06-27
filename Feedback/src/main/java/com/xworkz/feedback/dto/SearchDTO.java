package com.xworkz.feedback.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
public @ToString class SearchDTO
{

    private String rating;

    public SearchDTO()
    {
        System.out.println("SearchDTO constructor:");

    }
}
