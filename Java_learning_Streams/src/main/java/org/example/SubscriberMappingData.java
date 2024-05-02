package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberMappingData {

    private String imsi;
    private String dnnName;
    private String zoneVplmn;

    private String uePaaAddrType;


}
