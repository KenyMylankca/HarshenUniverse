package com.kenymylankca.harshenuniverse.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * The Event Annotation used for the custom inventory system. Treat like {@link SubscribeEvent}.
 * @author Wyn Price
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HarshenEvent {

}