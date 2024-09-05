import { Fab, Zoom } from '@mui/material';
import { ChevronUp } from 'lucide-react';
import { useEffect, useState } from 'react';

import ElementId from '@/enums/Element.id.enum';
import useScrollTo from '@/hooks/use.scroll.to';
import { ClassNameProp } from '@/types/prop.types';
import ClassUtils from '@/utils/Class.utils';

export interface ScrollToTopButtonProps extends Partial<ClassNameProp> {}

export default function ScrollToTopButton({ className }: ScrollToTopButtonProps) {
  const { scrollToId } = useScrollTo();
  const [visible, setVisible] = useState(false);

  useEffect(() => {
    const bodyElement = document.getElementById(ElementId.Body);

    if (!bodyElement) return () => {};

    const checkScrollTop = () => {
      if (!visible && bodyElement.scrollTop > 0) setVisible(true);
      else if (visible && bodyElement.scrollTop <= 0) setVisible(false);
    };

    bodyElement.addEventListener('scroll', checkScrollTop);
    return () => {
      bodyElement.removeEventListener('scroll', checkScrollTop);
    };
  }, [visible]);

  return (
    <Zoom in={visible}>
      <Fab
        className={ClassUtils.concat('!fixed bottom-4 right-6 !size-12 !p-0', className)}
        onClick={scrollToId(ElementId.Home)}
        sx={(theme) => ({
          ':hover': { backgroundColor: theme.palette.action.hover },
          backgroundColor: theme.palette.background.paper,
          color: theme.palette.text.primary,
        })}
      >
        <ChevronUp className="size-8" />
      </Fab>
    </Zoom>
  );
}
