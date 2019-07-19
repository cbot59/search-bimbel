package it.aldi.app.controller.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproveJobApplicationCmd {
    private Long jobAppId;
}
