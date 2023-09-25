package com.massafra.club.constants;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class PersistRabbitMq {
    private static final String PROTHEUS_PERSIST_QUEUE_PREFIX = "protheus-persist.";
    private static final String DEAD_SUFFIX = ".dead";
    public static final String EXCHANGE_PROTHEUS_COMMANDS = "protheus.commands";
    public static final String EXCHANGE_DEAD_PROTHEUS_COMMANDS = EXCHANGE_PROTHEUS_COMMANDS + DEAD_SUFFIX ;
    public static final String CREATE_ORDER_ROUTING_KEY = "create.order";
    public static final String ORDER_CREATE_QUEUE = PROTHEUS_PERSIST_QUEUE_PREFIX + CREATE_ORDER_ROUTING_KEY;
    private static final String DEAD = ".dead";
    public static final String ORDER_CREATE_QUEUE_DEAD = ORDER_CREATE_QUEUE + DEAD;
    public static final String CREATE_ORDER_DEAD_ROUTING_KEY = CREATE_ORDER_ROUTING_KEY + DEAD;
}
