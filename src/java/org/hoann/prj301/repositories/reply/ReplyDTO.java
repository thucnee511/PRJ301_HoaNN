package org.hoann.prj301.repositories.reply;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReplyDTO {

    private String feedbackId;
    private String description;
    private Timestamp time;
}
