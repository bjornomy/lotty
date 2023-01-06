package dev.myrold.api;

import java.util.List;

public record Lottery(
   String id,
   String name,
   List<Participant> participants,
   List<Drawing> drawings
) { }
