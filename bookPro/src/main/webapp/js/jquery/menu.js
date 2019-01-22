/**
 * Menu.js v0.17
 * (c) 2015-2018 zhangsilei
 * 
 * 基于jQuery的轻量级响应式菜单插件
 */

;
(function($, w) {
    'use strict';

    // 页面元素
    var $firstMenu = $('.ve-menu-pc'),
        $firstLinks = $firstMenu.children('li').children('a'),
        $sencondLinks = $firstMenu.find('ul').find('a'),
        hasSecond = $firstMenu.find('ul').length;

    // 菜单类构造方法
    var Menu = function(options) {
        // 自带主题: dark(黑底白字)、blue(蓝底白字)
        this.themeColor = {
            blue: {
                fontColor: '#fff',
                bgColor: '#0E90D2',
                hoverFontColor: '#fff',
                hoverBgColor: '#0C79B1'
            },
            dark: {
                fontColor: '#9D9D9D',
                bgColor: '#1F1F1F',
                hoverFontColor: '#fff',
                hoverBgColor: '#000'
            }
        };
        this.defaults = {
            fontSize: '16px',
            fontColor: this.themeColor.blue.fontColor,
            bgColor: this.themeColor.blue.bgColor,
            hoverFontColor: this.themeColor.blue.hoverFontColor,
            hoverBgColor: this.themeColor.blue.hoverBgColor,

            subFontSize: '16px',
            subFontColor: this.themeColor.blue.fontColor,
            subBgColor: this.themeColor.blue.bgColor,
            subHoverFontColor: this.themeColor.blue.hoverFontColor,
            subHoverBgColor: this.themeColor.blue.hoverBgColor,

            height: 40,
            itemWidth: 20,
            itemSpace: 1,
            theme: 'blue',
            mMenuBtnColor: '#000',
            mMaskColor: '#000',
            mBgColor: '#000',
            mFontColor: '#fff',
            mSubBgColor: '#222',
            mSubFontColor: '#fff',
            mCloseBtnColor: '#fff',

            animate: false,
            speed: 200
        };
        this.settings = $.extend({}, this.defaults, options);
        this.settings.itemWidth = this.settings.itemWidth - 0;
        this.isMobile = !!navigator.userAgent.match(/AppleWebKit.*Mobile.*/);
    }

    // 原型链上加属性和方法
    Menu.prototype = {
        init: function() {
            this.setDefaultTheme()
                .setDefinedTheme()
                .setFirstMenu()
                .setSecondMenu()
                .responsiveLayout()
                .supportFastClick();
        },
        setDefaultTheme: function() {
            var _this = this;
            // 设置默认主题
            switch (_this.settings.theme) {
                case 'blue':
                    setThemeColor(_this.themeColor.blue.fontColor, _this.themeColor.blue.bgColor);
                    !_this.isMobile && setHoverBgColor(_this.themeColor.blue.hoverFontColor, _this.themeColor.blue.hoverBgColor);
                    break;
                case 'dark':
                    setThemeColor(_this.themeColor.dark.fontColor, _this.themeColor.dark.bgColor);
                    !_this.isMobile && setHoverBgColor(_this.themeColor.dark.hoverFontColor, _this.themeColor.dark.hoverBgColor);
                    break;
            };
            // 设置默认主题颜色
            function setThemeColor(fontColor, bgColor) {
                $firstLinks.css({
                    color: fontColor,
                    backgroundColor: bgColor
                });
                $sencondLinks.css({
                    color: fontColor,
                    backgroundColor: bgColor
                });
            }
            // 设置鼠标滑过背景颜色
            function setHoverBgColor(fontColor, bgColor) {
                var normalFontColor, normalBgColor;
                $firstMenu.find('a').hover(function() {
                    var $this = $(this),
                        normalFontColor = $this.css('color');
                    normalBgColor = $this.css('backgroundColor');
                    $this.css({
                        color: fontColor,
                        backgroundColor: bgColor
                    });
                }, function() {
                    $(this).css({
                        color: normalFontColor,
                        backgroundColor: normalBgColor
                    });
                });
            }
            return _this;
        },
        setDefinedTheme: function() {
            var theme = this.settings.theme;
            if (theme && typeof theme == 'string') {
                if (theme != 'blue') {
                    this.settings.fontColor = this.themeColor[theme].fontColor;
                    this.settings.bgColor = this.themeColor[theme].bgColor;
                    this.settings.hoverFontColor = this.themeColor[theme].hoverFontColor;
                    this.settings.hoverBgColor = this.themeColor[theme].hoverBgColor;
                    this.settings.subFontColor = this.themeColor[theme].fontColor;
                    this.settings.subBgColor = this.themeColor[theme].bgColor;
                    this.settings.subHoverFontColor = this.themeColor[theme].hoverFontColor;
                    this.settings.subHoverBgColor = this.themeColor[theme].hoverBgColor;
                }
            }
            return this;
        },
        setFirstMenu: function() {
            var _this = this;
            $firstLinks.css({
                height: _this.settings.height,
                lineHeight: _this.settings.height + 'px',
                fontSize: _this.settings.fontSize,
                color: _this.settings.fontColor,
                backgroundColor: _this.settings.bgColor
            }).parent('li').each(function(index, el) {
                var $this = $(this);
                $this.width($this.width() + _this.settings.itemWidth); // 一级菜单宽度
            }).not($firstMenu.children('li').first()).css({
                marginLeft: this.settings.itemSpace + 'px' // 菜单间隙
            });
            return _this;
        },
        setSecondMenu: function() {
            var _this = this;
            if (hasSecond) {
                $sencondLinks.css({
                        height: _this.settings.height,
                        lineHeight: _this.settings.height + 'px',
                        fontSize: _this.settings.subFontSize,
                        color: _this.settings.subFontColor,
                        backgroundColor: _this.settings.subBgColor
                    }).parent('li').css({
                        height: _this.settings.height,
                        lineHeight: _this.settings.height + 'px'
                    })
                    // 二级菜单宽度
                $.each($firstLinks, function(index, val) {
                    var $secondMenu = $(this).next('ul');
                    $secondMenu.css('top', _this.settings.height);
                    if ($secondMenu.width() + _this.settings.itemWidth < $firstLinks.width()) {
                        $secondMenu.width($firstLinks.width());
                    } else {
                        $secondMenu.width($secondMenu.width() + _this.settings.itemWidth);
                    }
                });
            }
            return _this;
        },
        setHoverCss: function() {
            var _this = this;
            // 一级菜单样式
            $firstLinks.hover(function() {
                $(this).css({
                    color: _this.settings.hoverFontColor,
                    backgroundColor: _this.settings.hoverBgColor
                })
            }, function() {
                $(this).css({
                    color: _this.settings.fontColor,
                    backgroundColor: _this.settings.bgColor
                })
            });
            // 二级菜单样式
            $sencondLinks.hover(function() {
                var $this = $(this);
                $this.css({
                    color: _this.settings.subHoverFontColor,
                    backgroundColor: _this.settings.subHoverBgColor
                });
                $this.parents('ul').first().prev().css({
                    color: _this.settings.hoverFontColor,
                    backgroundColor: _this.settings.hoverBgColor
                });
            }, function() {
                var $this = $(this);
                $this.css({
                    color: _this.settings.subFontColor,
                    backgroundColor: _this.settings.subBgColor
                });
                $this.parents('ul').first().prev().css({
                    color: _this.settings.fontColor,
                    backgroundColor: _this.settings.bgColor
                });
            });
        },
        responsiveLayout: function() {
            var _this = this,
                screenWidth = $(w).width(),
                $menuIcon = $('.ve-menu-icon'),
                animate = _this.settings.animate;
            if (screenWidth <= 768) {
                if (!$menuIcon.length) {
                    // 隐藏原菜单
                    $firstMenu.hide();
                    // 添加菜单按钮
                    $firstMenu.after('<div class="ve-menu-icon"><div></div><div></div><div></div></div>');
                    $menuIcon = $('.ve-menu-icon');
                    $menuIcon.css('marginTop', 10)
                        .on('click', function() {
                            // 创建遮罩和一、二级菜单
                            if (!$('.ve-menu-mask').length && !$('.ve-menu-mobile').length) {
                                $('<div class="ve-menu-mask"></div>')
                                    .css('background', _this.settings.mMaskColor).appendTo('.ve-menu')
                                    .after('<ul class="ve-menu-mobile"><li class="ve-menu-close"><div></div></li>' + $firstMenu.html() + '</ul>')
                                    .next().find('ul, li, a')
                                    .removeAttr('style');
                            }

                            // 动画显示一级菜单和遮罩层
                            if (animate) {
                                _this.animate($('.ve-menu-mask'), animate, _this.settings.speed, 'open');
                                _this.animate($('.ve-menu-mobile'), animate, _this.settings.speed, 'open');
                            } else {
                                $('.ve-menu-mask').show();
                                $('.ve-menu-mobile').show();
                            }

                            // 二级菜单样式
                            $('.ve-menu-mobile > li').not('.ve-menu-close')
                                .find('ul').find('a')
                                .css({ //  二级菜单样式
                                    background: _this.settings.mSubBgColor,
                                    color: _this.settings.mSubFontColor
                                });

                            // 一级菜单点击事件，每次打开一级菜单时重新绑定，防止重复
                            $('.ve-menu-mobile > li').not('.ve-menu-close')
                                .unbind('click')
                                .on('click', function(e) {
                                    // 暂时不支持自定义动画，默认为toggle动效
                                    // TODO 添加自定义动画
                                    // 二级菜单动画
                                    if (animate) {
                                        $(this).find('ul').toggle(150);
                                    } else {
                                        $(this).find('ul').toggle();
                                    }
                                });

                            // 关闭按钮样式、点击事件
                            $('.ve-menu-close').css('color', _this.settings.mCloseBtnColor)
                                .children('div')
                                .on('click', function() {
                                    // 动画结束
                                    if (animate) {
                                        _this.animate($('.ve-menu-mask'), animate, _this.settings.speed, 'close');
                                        _this.animate($('.ve-menu-mobile'), animate, _this.settings.speed, 'close');
                                    } else {
                                        $('.ve-menu-mask').hide();
                                        $('.ve-menu-mobile').hide();
                                    }
                                });
                        })
                        .children('div')
                        .css('background', _this.settings.mMenuBtnColor);
                } else {
                    $firstMenu.hide();
                    $menuIcon.show();
                }
            } else {
                if ($menuIcon.length) {
                    $firstMenu.show();
                    $menuIcon.hide();
                }
            }

            return _this;
        },
        supportFastClick: function() {
            if (this.isMobile) { // 在移动端打开页面，将事件触发改为click
                try {
                    if (FastClick) {
                        FastClick.attach(document.body); // 支持fastclick
                    }
                } catch (e) {
                    console.log('fastclick.js is not found.You are still using normal \'click\'');
                }
            } else { // 在PC端打开页面，事件触发仍为hover
                // if (hasSecond) {
                this.setHoverCss();
                // }
            }
            return this;
        },
        animate: function($ele, type, speed, openOrClose) {
            var startStyle = {},
                endStyle = {};

            switch (type) {
                case 'fade':
                    if (openOrClose == 'open') {
                        $ele.hide();
                        $ele.fadeIn(speed);
                    } else if (openOrClose == 'close') {
                        $ele.fadeOut(speed);
                    }
                    // $ele.css('opacity', 0);
                    // startStyle.opacity = 1;
                    // endStyle.opacity = 0;
                    break;
                case 'slide':
                    $ele.stop(true, true);
                    if (openOrClose == 'open') {
                        startStyle.height = 0;
                        endStyle.height = '100%';
                    } else if (openOrClose == 'close') {
                        startStyle.height = '100%';
                        endStyle.height = 0;
                    }
                    $ele.css(startStyle).animate(endStyle, speed);
                    break;
            }
        }
    }

    // 将插件挂到jQuery下并初始化
    $.fn.menu = function(options) {
        var menu = new Menu(options);
        menu.init();
        $(window).resize(function(event) {
            menu.responsiveLayout()
        });
        return this;
    }

})(jQuery, window);