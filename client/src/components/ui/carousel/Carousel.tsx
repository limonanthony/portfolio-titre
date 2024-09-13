import { faker } from '@faker-js/faker';
import { Box, IconButton } from '@mui/material';
import { ArrowLeftIcon, ArrowRightIcon } from 'lucide-react';
import { JSX, useState } from 'react';

import { ClassNameProp } from '@/types/prop.types.ts';
import ClassUtils from '@/utils/Class.utils.ts';

export interface CarouselProps extends Partial<ClassNameProp> {
  elements: JSX.Element[];
}

function CarouselButton({ className, icon, onClick }: { className?: string, icon: JSX.Element, onClick: () => void }) {
  return (
    <div className={ClassUtils.concat('z-[2] flex items-center', className)}>
      <IconButton
        onClick={() => onClick?.()}
        size="large"
      >
        {icon}
      </IconButton>
    </div>
  );
}

export default function Carousel(props: CarouselProps) {
  const { elements } = props;
  const [selectedContent, setSelectedContent] = useState<number>(0);

  function goNextContent() {
    setSelectedContent((prev) => (prev + 1 > elements.length - 1 ? 0 : prev + 1));
  }

  function goPrevContent() {
    setSelectedContent((prev) => (prev - 1 < 0 ? elements.length - 1 : prev - 1));
  }

  return (
    <div className="relative flex size-full flex-1 overflow-x-hidden">
      <CarouselButton
        className="absolute left-0 top-1/2 -translate-y-1/2"
        icon={<ArrowLeftIcon />}
        onClick={() => goPrevContent()}
      />
      <div className="flex flex-1 flex-col overflow-x-hidden">
        <Box
          className="flex flex-1 transition-transform duration-500 ease-in-out"
          style={{
            transform: `translateX(-${selectedContent * 100}%)`,
          }}
        >
          {elements.map((item) => (
            <div
              className={ClassUtils.concat('flex min-h-full min-w-full flex-1 flex-col items-center justify-center')}
              key={faker.string.uuid()}
            >
              {item}
            </div>
          ))}
        </Box>
        <div />
      </div>
      <CarouselButton
        className="absolute right-0 top-1/2 -translate-y-1/2"
        icon={<ArrowRightIcon />}
        onClick={() => goNextContent()}
      />
    </div>
  );
}
