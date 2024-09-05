import { Avatar } from '@mui/material';

import ProfileAvatar from '@/assets/images/profile-avatar.png';
import NavBarLink from '@/components/ui/links/NavBarLink';

export interface ProfileElementProps {
  onClick?: () => void;
}

export default function ProfileElement({ onClick }: ProfileElementProps) {
  return (
    <NavBarLink className="flex cursor-pointer items-center gap-6" onClick={onClick}>
      <Avatar className="transition-all duration-300" src={ProfileAvatar} />
      {import.meta.env.VITE_AUTHOR.toUpperCase()}
    </NavBarLink>
  );
}
