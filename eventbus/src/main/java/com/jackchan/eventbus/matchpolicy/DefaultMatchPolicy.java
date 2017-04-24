/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 Umeng, Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.jackchan.eventbus.matchpolicy;


import com.jackchan.eventbus.EventType;

import java.util.LinkedList;
import java.util.List;
/**
 * ============================================================
 * Copyright：JackChan和他的朋友们有限公司版权所有 (c) 2017
 * Author：   JackChan
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChan1999
 * GitBook：  https://www.gitbook.com/@alleniverson
 * 博客：     http://blog.csdn.net/axi295309066
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：FocusToday
 * Package_Name：com.jackchan.eventbus
 * Version：1.0
 * time：2016/4/25 0:46
 * des ：${TODO}
 * gitVersion：2.12.0.windows.1
 * updateAuthor：JackChan
 * updateDate：2016/4/25 0:46
 * updateDes：${TODO}
 * ============================================================
 */
public class DefaultMatchPolicy implements MatchPolicy {

    @Override
    public List<EventType> findMatchEventTypes(EventType type, Object aEvent) {
        Class<?> eventClass = aEvent.getClass();
        List<EventType> result = new LinkedList<EventType>();
        while (eventClass != null) {
            result.add(new EventType(eventClass, type.tag));
            addInterfaces(result, eventClass, type.tag);
            eventClass = eventClass.getSuperclass();
        }

        return result;
    }

    /**
     * 获取该事件的所有接口类型
     * 
     * @param eventTypes 存储列表
     * @param interfaces 事件实现的所有接口
     */
    private void addInterfaces(List<EventType> eventTypes, Class<?> interfaces, String tag) {
        if (interfaces == null) {
            return;
        }
        Class<?>[] interfacesClasses = interfaces.getInterfaces();
        for (Class<?> interfaceClass : interfacesClasses) {
            if (!eventTypes.contains(interfaceClass)) {
                eventTypes.add(new EventType(interfaceClass, tag));
                addInterfaces(eventTypes, interfaceClass, tag);
            }
        }
    }

}
